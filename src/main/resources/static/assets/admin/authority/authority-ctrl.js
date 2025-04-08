app.controller("authority-ctrl", function($scope, $http) {
    // Khởi tạo danh sách quyền
    $scope.items = [];
    $scope.form = {};
    $scope.pageCount = 0;

    // Khởi tạo dữ liệu ban đầu
    $scope.initialize = function() {
        $http.get("/rest/authorities").then(resp => {
            $scope.items = resp.data;
            $scope.pageCount = Math.ceil($scope.items.length / 8);
        }).catch(error => {
            alert("Lỗi khi tải danh sách quyền");
            console.error("Error", error);
        });
    };

    // Phân trang
    $scope.begin = 0;
    $scope.currentPage = 1;

    $scope.last = function() {
        $scope.begin = ($scope.pageCount - 1) * 8;
        $scope.currentPage = $scope.pageCount;
    };

    $scope.first = function() {
        $scope.begin = 0;
        $scope.currentPage = 1;
    };

    $scope.next = function() {
        if ($scope.begin < ($scope.pageCount - 1) * 8) {
            $scope.begin += 8;
            $scope.currentPage++;
        } else {
            alert("Đang ở trang cuối cùng");
        }
    };

    $scope.prev = function() {
        if ($scope.begin > 0) {
            $scope.begin -= 8;
            $scope.currentPage--;
        } else {
            alert("Đang ở trang đầu");
        }
    };

    // Reset form
	$scope.reset = function() {
	    $scope.form = {
	        id: "",
	        account: {
	            username: "",
	            password: "",
	            fullname: "",
	            email: "",
	            photo: ""
	        },
	        role: { id: "" } // ✅ Role phải là object
	    };
	};


    // Thêm mới quyền
	$scope.create = function() {
	    var item = angular.copy($scope.form);
	    item.role = { id: item.role.id }; // ✅ Đảm bảo đúng kiểu
	    $http.post("/rest/authorities", item).then(resp => {
	        $scope.items.push(resp.data);
	        $scope.reset();
	        alert("Thêm mới quyền thành công");
	    }).catch(error => {
	        alert("Lỗi thêm mới quyền");
	        console.log("Error", error);
	    });
	};


    // Cập nhật quyền
    $scope.update = function() {
        var item = angular.copy($scope.form);
        $http.put("/rest/authorities/" + item.id, item).then(resp => {
            var index = $scope.items.findIndex(a => a.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật quyền thành công");
        }).catch(error => {
            alert("Lỗi cập nhật quyền");
            console.log("Error", error);
        });
    };

    // Xóa quyền
    $scope.delete = function(a) {
        if (!a || !a.id) {
            alert("Không tìm thấy quyền cần xóa!");
            return;
        }

        if (!confirm("Bạn có chắc chắn muốn xóa quyền này?")) return;

        $http.delete("/rest/authorities/" + a.id).then(resp => {
            var index = $scope.items.findIndex(item => item.id == a.id);
            if (index !== -1) {
                $scope.items.splice(index, 1);
            }
            alert("Xóa quyền thành công!");
        }).catch(error => {
            alert("Lỗi xóa quyền!");
            console.error("Error:", error);
        });
    };

    // Sửa quyền
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
    };

    // Khởi tạo dữ liệu khi controller được load
    $scope.initialize();
});
