function Hello($scope, $http) {
	$http.get('http://localhost:8080/WebServiceExample/rest/hello/user').
	success(function(data) {
		$scope.user = data;
	});
}

function myCtrl($scope) {
	$scope.firstNameValue = "John";
	$scope.lastNameValue = "Doe";
}

function listController($scope) {
	$scope.names = [
	    {name:'Jani',country:'Norway'},
	    {name:'Hege',country:'Sweden'},
	    {name:'Kai',country:'Denmark'}
    ];
}

function tableController($scope) {
	$scope.vals = [
	    {name:'Jani',country:'Norway'},
	    {name:'Hege',country:'Sweden'},
	    {name:'Kai',country:'Denmark'}
    ];
}
