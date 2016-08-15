app.controller("productsController", function ($scope, $http) {

    $http.get('http://localhost:8081/api/product').success(function (data) {
        $scope.products = data;
    });
});