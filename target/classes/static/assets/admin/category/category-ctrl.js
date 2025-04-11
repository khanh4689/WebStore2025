app.controller("category-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.pageCount = 0;

	// Khởi tạo dữ liệu ban đầu
	$scope.initialize = function() {
		$http.get("/rest/categories").then(resp => {
			$scope.items = resp.data;
			$scope.pageCount = Math.ceil($scope.items.length / 8);
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
			name: ""
		};
	};

	// Thêm mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post("/rest/categories", item).then(resp => {
			$scope.items.push(resp.data);
			$scope.reset();
			alert("Thêm mới thành công");
		}).catch(error => {
			alert("Lỗi thêm mới danh mục");
			console.log("Error", error);
		});
	};

	// Cập nhật
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put("/rest/categories/" + item.id, item).then(resp => {
			var index = $scope.items.findIndex(c => c.id == item.id);
			$scope.items[index] = item;
			alert("Cập nhật thành công");
		}).catch(error => {
			alert("Lỗi cập nhật danh mục");
			console.log("Error", error);
		});
	};

	// Xóa
	$scope.delete = function(c) {
		if (!c || !c.id) {
			alert("Không tìm thấy danh mục cần xóa!");
			return;
		}

		if (!confirm("Bạn có chắc chắn muốn xóa danh mục này?")) return;

		$http.delete("/rest/categories/" + c.id).then(resp => {
			var index = $scope.items.findIndex(item => item.id == c.id);
			if (index !== -1) {
				$scope.items.splice(index, 1);
			}
			alert("Xóa thành công!");
		}).catch(error => {
			alert("Lỗi xóa danh mục!");
			console.error("Error:", error);
		});
	};

	// Sửa
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	};

	$scope.initialize();
});
