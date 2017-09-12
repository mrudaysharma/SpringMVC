'use strict';

angular.module('myApp').controller('RoomController', ['$scope', 'RoomService', function($scope, RoomService) {
    var self = this;
    self.room={id:null,name:''};
    self.rooms=[];

    /*self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;*/


    fetchAllRooms();

    function fetchAllRooms(){
        RoomService.fetchAllRooms()
            .then(
            function(d) {
                self.rooms = d;
            },
            function(errResponse){
                console.error('Error while fetching Rooms');
            }
        );
    }

   /*function createRoom(room){
        RoomService.createRoom(room)
            .then(
            fetchAllRooms,
            function(errResponse){
                console.error('Error while creating Room');
            }
        );
    }

    function updateRoom(room, id){
        RoomService.updateRoom(room, id)
            .then(
            fetchAllRooms,
            function(errResponse){
                console.error('Error while updating Room');
            }
        );
    }

    function deleteRoom(id){
        RoomService.deleteRoom(id)
            .then(
            fetchAllRooms,
            function(errResponse){
                console.error('Error while deleting Room');
            }
        );
    }

    function submit() {
        if(self.room.id===null){
            console.log('Saving New Room', self.room);
            createRoom(self.room);
        }else{
            updateRoom(self.room, self.room.id);
            console.log('Room updated with id ', self.room.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.rooms.length; i++){
            if(self.rooms[i].id === id) {
                self.room = angular.copy(self.rooms[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.room.id === id) {//clean form if the room to be deleted is shown there.
            reset();
        }
        deleteRoom(id);
    }


    function reset(){
        self.room={id:null,name:''};
        $scope.myForm.$setPristine(); //reset Form
    }*/

}]);
