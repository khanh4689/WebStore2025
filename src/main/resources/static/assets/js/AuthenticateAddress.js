app.controller("authenticateAddressCtrl", function($scope, $http) {
  $scope.order = {};
  $scope.provinces = [];
  $scope.districts = [];
  $scope.wards = [];

  $scope.loadProvinces = function () {
    $http.get('https://provinces.open-api.vn/api/?depth=1').then(resp => {
      $scope.provinces = resp.data;
    });
  };

  $scope.loadDistricts = function () {
    $http.get('https://provinces.open-api.vn/api/p/' + $scope.selectedProvince.code + '?depth=2')
      .then(resp => {
        $scope.districts = resp.data.districts;
      });
  };

  $scope.loadWards = function () {
    $http.get('https://provinces.open-api.vn/api/d/' + $scope.selectedDistrict.code + '?depth=2')
      .then(resp => {
        $scope.wards = resp.data.wards;
      });
  };

  $scope.submitOrder = function () {
    if (!$scope.selectedProvince || !$scope.selectedDistrict || !$scope.selectedWard || !$scope.detailAddress) {
      alert("Vui lòng nhập đầy đủ địa chỉ!");
      return;
    }

    // Gộp địa chỉ
    $scope.order.address =
      $scope.detailAddress + ', ' +
      $scope.selectedWard.name + ', ' +
      $scope.selectedDistrict.name + ', ' +
      $scope.selectedProvince.name;

    // Gửi order về backend
    $http.post('/rest/orders', $scope.order).then(resp => {
      alert("Đặt hàng thành công!");
    }).catch(error => {
      alert("Lỗi khi đặt hàng: " + (error.data.message || "Không xác định"));
    });
  };

  // Khởi tạo khi load
  $scope.loadProvinces();
});
