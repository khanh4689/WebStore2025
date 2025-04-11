app.controller("authority-ctrl", function ($scope, $http, $timeout, $httpParamSerializerJQLike) {
 
    $scope.roles = [];
    $scope.admins = [];
    $scope.authorities = [];

    $scope.initialize = function () {
        // Load all roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        });

        // Load all accounts
        $http.get("/rest/accounts").then(resp => {
            $scope.admins = resp.data;
        });

        // Load all authorities
        $http.get("/rest/authorities").then(resp => {
            console.log("Authorities data:", resp.data);
            $scope.authorities = resp.data;
        });
    };

    $scope.authority_of = function (acc, role) {
        if ($scope.authorities) {
            return $scope.authorities.find(ur =>
                ur.account.username == acc.username &&
                ur.role.id == role.id
            );
        }
    };

	$scope.authority_changed = function (acc, role) {
	    var authority = $scope.authority_of(acc, role);
	    var hasBlock = $scope.authorities.some(a =>
	        a.account.username === acc.username &&
	        a.role.id === 'BLOCK'
	    );
	    var hasOtherRoles = $scope.authorities.some(a =>
	        a.account.username === acc.username &&
	        a.role.id !== 'BLOCK'
	    );

	    // Nếu đang có BLOCK và người dùng bấm thêm role khác
	    if (hasBlock && role.id !== 'BLOCK' && !authority) {
	        alert("Không thể thêm quyền khác khi tài khoản đã bị BLOCK!");
			$timeout(() => location.reload(), 50);
	        return;
	    }
		

	    // Nếu đang có role khác và người dùng bấm thêm BLOCK
	    if (hasOtherRoles && role.id === 'BLOCK' && !authority) {
	        alert("Không thể thêm BLOCK khi tài khoản đang có quyền khác!");
			$timeout(() => location.reload(), 50);
	        return;
	    }

	    if (authority) {
	        $scope.revoke_authority(authority);
	    } else {
	        authority = { account: acc, role: role };
	        $scope.grant_authority(authority);
	    }
	};


    $scope.grant_authority = function (authority) {
        $http.post("/rest/authorities", authority).then(resp => {
            $scope.authorities.push(resp.data);
            alert("Cấp quyền thành công");
        }).catch(error => {
            console.log("error", error);
            if (error.status == 403) {
                alert("Tài Khoản của bạn không đủ thẩm quyền");
            } else {
                alert("Cấp quyền thất bại");
            }
        });
    };

    $scope.revoke_authority = function (authority) {
        $http.delete("/rest/authorities/" + authority.id).then(resp => {
            var index = $scope.authorities.findIndex(a => a.id == authority.id);
            $scope.authorities.splice(index, 1);
            alert("Thu hồi thành công");
        }).catch(error => {
            console.log("error", error);
            if (error.status == 403) {
                alert("Tài Khoản của bạn không đủ thẩm quyền");
            } else {
                alert("Thu hồi thất bại");
            }
        });
    };

    $scope.form = {
        account: {},
        roles: []
    };

	$scope.toggleRole = function (roleId) {
	    const idx = $scope.form.roles.indexOf(roleId);

	    if (idx > -1) {
	        $scope.form.roles.splice(idx, 1); // Bỏ chọn
	    } else {
	        // Nếu chọn BLOCK và đã có quyền khác
	        if (roleId === 'BLOCK' && $scope.form.roles.length > 0) {
	            alert("Không thể chọn BLOCK cùng với các quyền khác!");
	            $timeout(() => location.reload(), 100); // ✅ dùng $timeout
	            return;
	        }

	        // Nếu chọn quyền khác và đã có BLOCK
	        if (roleId !== 'BLOCK' && $scope.form.roles.includes('BLOCK')) {
	            alert("Không thể chọn thêm quyền khác khi đã có BLOCK!");
	            $timeout(() => location.reload(), 100);
	            return;
	        }

	        $scope.form.roles.push(roleId); // Chọn thêm quyền
	    }
	};





	  $scope.create = function () {
	      const data = angular.copy($scope.form.account);
	      const params = $httpParamSerializerJQLike({ roleIds: $scope.form.roles });

	      // Gọi tới /accounts/create
	      $http({
	          method: "POST",
	          url: "/accounts/create?" + params,
	          data: data,
	          headers: {
	              "Content-Type": "application/json"
	          }
	      }).then(resp => {
	          alert("Tạo tài khoản thành công!");

	          // Gọi thêm /authorities cho từng quyền (nếu bạn muốn đảm bảo từng quyền được tạo riêng)
	          $scope.form.roles.forEach(roleId => {
	              const auth = {
	                  account: { username: $scope.form.account.username },
	                  role: { id: roleId }
	              };

	              $http.post("/authorities", auth)
	                  .then(authResp => {
	                      console.log("Tạo quyền thành công:", authResp.data);
	                  })
	                  .catch(authErr => {
	                      console.error("Lỗi khi tạo quyền riêng:", authErr);
	                  });
	          });

	          $scope.reset();
	      }).catch(error => {
	          console.error("Lỗi khi tạo tài khoản:", error);
	          alert("Tạo tài khoản thất bại!");
	      });
	  };

    $scope.reset = function () {
        $scope.form = {
            account: {},
            roles: []
        };
    };

    $scope.initialize();
});
