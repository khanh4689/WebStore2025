app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider) {
	$routeProvider
		.when("/product", {
			templateUrl: "/assets/admin/product/index.html",
			controller: "product-ctrl"
		})
		.when("/category", {
			templateUrl: "/assets/admin/category/_index.html",
			controller: "category-ctrl"
		})
		.when("/order", {
			templateUrl: "/assets/admin/order/index.html",
			controller: "order-ctrl"
		})
		.when("/authorize", {
			templateUrl: "/assets/admin/authority/index.html",
			controller: "authority-ctrl"
		})
		.when("/unauthorized", {
			templateUrl: "/assets/admin/authority/unauthorized.html",
			controller: "authority-ctrl"
		})

})