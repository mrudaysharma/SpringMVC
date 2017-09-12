'use strict';

angular.module('myApp').factory('PatientService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8084/scapeapp/patient/';

    var factory = {
        fetchAllPatients: fetchAllPatients,
        createPatient: createPatient,
        updatePatient:updatePatient
        //deletePatient:deletePatient
    };

    return factory;

     function fetchAllPatients() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+"all")
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

    function createPatient(patient) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI+"createOrupdate", patient)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Patient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updatePatient(patient, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+"/createOrupdate", patient)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Patient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

   /* function deletePatient(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Patient');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }*/

}]);
