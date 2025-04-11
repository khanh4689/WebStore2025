app.controller("order-ctrl", function($scope, $http) {
    $scope.orders = [];

    // Khởi tạo dữ liệu
    $scope.initialize = function() {
        $http.get("/rest/orders").then(resp => {
            $scope.orders = resp.data;
        }).catch(error => {
            alert("Lỗi tải danh sách đơn hàng!");
            console.log(error);
        });
    };

    // Xem chi tiết đơn hàng
    $scope.viewDetails = function(order) {
        $scope.selectedOrder = order;
    };
	
	$scope.updateStatus = function(order) {
	    $http.put("/rest/orders/" + order.id + "/status", order)
	        .then(resp => {
	            alert("Cập nhật trạng thái thành công!");
	        }).catch(error => {
	            alert("Lỗi cập nhật trạng thái!");
	            console.log(error);
	        });
	};
	
	$scope.selectedOrder = null;

	$scope.viewOrder = function(order) {
	    $http.get("/rest/orders/" + order.id).then(resp => {
	        $scope.selectedOrder = resp.data;
	        $('#orderModal').modal('show'); // gọi modal lên nếu có dùng Bootstrap
	    });
	};



    // Xoá đơn hàng
    $scope.delete = function(order) {
        if (!confirm("Bạn có chắc chắn muốn xoá đơn hàng #" + order.id + "?")) return;
        $http.delete("/rest/orders/" + order.id).then(resp => {
            let index = $scope.orders.findIndex(o => o.id === order.id);
            $scope.orders.splice(index, 1);
            alert("Xoá thành công!");
        }).catch(error => {
            alert("Lỗi khi xoá đơn hàng!");
            console.error(error);
        });
    };

    $scope.initialize();
});
