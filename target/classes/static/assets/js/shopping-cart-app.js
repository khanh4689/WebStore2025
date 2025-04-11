const app = angular.module("shopping-cart-app", []);


app.controller("shopping-cart-ctrl", function ($scope, $http) {
	// Địa chỉ từ API
	$scope.provinces = [];
	$scope.districts = [];
	$scope.wards = [];

	$scope.selectedProvince = null;
	$scope.selectedDistrict = null;
	$scope.selectedWard = null;
	$scope.street = "";
    $scope.cart = {
        items: [],

        // Thêm sản phẩm vào giỏ hàng
		add(id) {
		    var item = this.items.find(item => item.id == id);
		    
		    if (item) {
		        // Nếu sản phẩm đã có trong giỏ, tăng số lượng lên 1
		        item.qty++;
		        this.saveToLocalStorage();
		        
		        // Chuyển hướng sau khi lưu giỏ hàng
		        window.location.href = '/cart/view';
		    } else {
		        // Nếu sản phẩm chưa có trong giỏ, thực hiện yêu cầu HTTP để lấy thông tin sản phẩm
		        $http.get(`/rest/products/${id}`).then(resp => {
		            resp.data.qty = 1;
		            this.items.push(resp.data);
		            this.saveToLocalStorage();
		            
		            // Chuyển hướng sau khi sản phẩm được thêm vào giỏ
		            window.location.href = '/cart/view';
		        }).catch(error => {
		            console.error("Không thể tải sản phẩm:", error);
		        });
		    }
		},

        // Xóa một sản phẩm khỏi giỏ hàng
        remove(id) {
            this.items = this.items.filter(item => item.id !== id);
            this.saveToLocalStorage();
        },

        

        // Tính tổng tiền của một sản phẩm
        amt_of(item) {
            return item.qty * item.price;
        },
		
		clear() {
		    if (confirm("Bạn có chắc chắn muốn xóa toàn bộ giỏ hàng không?")) {
		        this.items = [];
		        this.saveToLocalStorage();
		    }
		},


        // Tính tổng số lượng sản phẩm
        get count() {
            return this.items.reduce((total, item) => total + item.qty, 0);
        },

        // Tính tổng tiền giỏ hàng
        get amount() {
            return this.items.reduce((total, item) => total + item.qty * item.price, 0);
        },

        // Lưu giỏ hàng vào localStorage
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        // Tải giỏ hàng từ localStorage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

     
    };

    // Khởi động: tải giỏ hàng từ localStorage
    $scope.cart.loadFromLocalStorage();
	// Load Tỉnh/Thành
	$scope.loadProvinces = function () {
	    $http.get("https://provinces.open-api.vn/api/p/").then(resp => {
	        $scope.provinces = resp.data;
	    });
	};

	// Load Quận/Huyện khi chọn tỉnh
	$scope.loadDistricts = function () {
	    if (!$scope.selectedProvince) return;
	    $http.get(`https://provinces.open-api.vn/api/p/${$scope.selectedProvince.code}?depth=2`)
	        .then(resp => {
	            $scope.districts = resp.data.districts;
	            $scope.selectedDistrict = null;
	            $scope.wards = [];
	            $scope.selectedWard = null;
	            $scope.updateAddress();
	        });
	};

	// Load Phường/Xã khi chọn huyện
	$scope.loadWards = function () {
	    if (!$scope.selectedDistrict) return;
	    $http.get(`https://provinces.open-api.vn/api/d/${$scope.selectedDistrict.code}?depth=2`)
	        .then(resp => {
	            $scope.wards = resp.data.wards;
	            $scope.selectedWard = null;
	            $scope.updateAddress();
	        });
	};

	// Gộp địa chỉ lại
	$scope.updateAddress = function () {
	    const parts = [
	        $scope.street,
	        $scope.selectedWard?.name,
	        $scope.selectedDistrict?.name,
	        $scope.selectedProvince?.name
	    ].filter(Boolean); // Lọc bỏ undefined/null
	    $scope.order.address = parts.join(', ');
	};


	// Theo dõi thay đổi để cập nhật địa chỉ
	$scope.$watchGroup(['street', 'selectedProvince', 'selectedDistrict', 'selectedWard'], function () {
	    $scope.updateAddress();
	});

	// Gọi khi load trang
	$scope.loadProvinces();
	$scope.order ={
		createDate: new Date(),
		address: "",
		account:{username:$("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item =>{
				return{
					product:{id:item.id},
					price:item.price,
					quantity:item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			$http.post("/rest/orders",order).then(resp =>{
				alert("Đặt hàng thành công!");
				$scope.cart.clear();
				location.href="/order/details/" + resp.data.id;
			}).catch(error =>{
				alert("Đặt hàng lỗi!")
				console.log(error)
			})
		}
	}
});
