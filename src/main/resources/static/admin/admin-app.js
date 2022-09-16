app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/product", {
		templateUrl: "/admin/product/index.html",
		controller: "product-ctrl"
	})
	.when("/category", {
		templateUrl: "/admin/category/index.html",
		controller: "category-ctrl"
	})
	.when("/account", {
		templateUrl: "/admin/account/index.html",
		controller: "account-ctrl"
	})
	.when("/authorize", {
		templateUrl: "/admin/authority/index.html",
		controller: "authority-ctrl"
	})
	.when("/unauthorized", {
		templateUrl: "/admin/authority/unauthorized.html",
		controller: "authority-ctrl"
	})
	.otherwise({
		template: "<h1 class='text-center'>Chào mừng bạn.</h1>",
	})
})