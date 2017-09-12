'use strict';

angular.module('myApp').factory('DoctorService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8084/scapeapp/doctor/';

    var factory = {
        fetchAllDoctors: fetchAllDoctors
        /*createDoctor: createDoctor,
        updateDoctor:updateDoctor,
        deleteDoctor:deleteDoctor*/
    };

    return factory;

    function fetchAllDoctors() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

   /* function createDoctor(doctor) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, doctor)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Doctor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateDoctor(doctor, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, doctor)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Doctor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteDoctor(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Doctor');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }*/

}]);
