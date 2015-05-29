function Hello($scope, $http) {
    $http.get('http://localhost:8080/WebServiceExample/rest/hello/user').
        success(function(data) {
            $scope.user = data;
        });
}