'use strict';

angular.module('myApp').factory('RoomService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8084/scapeapp/room/';

    var factory = {
        fetchAllRooms: fetchAllRooms
        /*createRoom: createRoom,
        updateRoom:updateRoom,
        deleteRoom:deleteRoom*/
    };

    return factory;

     function fetchAllRooms() {
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

   /* function createRoom(room) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, room)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Room');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateRoom(room, id) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, room)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Room');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteRoom(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Room');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }*/

}]);
