'use strict';

angular.module('myApp').controller('DoctorController', ['$scope', 'DoctorService', function($scope, DoctorService) {
    var self = this;
    self.doctor={id:null,name:''};
    self.doctors=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllDoctors();

    function fetchAllDoctors(){
        DoctorService.fetchAllDoctors()
            .then(
            function(d) {
                self.doctors = d;
            },
            function(errResponse){
                console.error('Error while fetching Doctors');
            }
        );
    }

    function createDoctor(doctor){
        DoctorService.createDoctor(doctor)
            .then(
            fetchAllDoctors,
            function(errResponse){
                console.error('Error while creating Doctor');
            }
        );
    }

    function updateDoctor(doctor, id){
        DoctorService.updateDoctor(doctor, id)
            .then(
            fetchAllDoctors,
            function(errResponse){
                console.error('Error while updating Doctor');
            }
        );
    }

    function deleteDoctor(id){
        DoctorService.deleteDoctor(id)
            .then(
            fetchAllDoctors,
            function(errResponse){
                console.error('Error while deleting Doctor');
            }
        );
    }

    function submit() {
        if(self.doctor.id===null){
            console.log('Saving New Doctor', self.doctor);
            createDoctor(self.doctor);
        }else{
            updateDoctor(self.doctor, self.doctor.id);
            console.log('Doctor updated with id ', self.doctor.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.doctors.length; i++){
            if(self.doctors[i].id === id) {
                self.doctor = angular.copy(self.doctors[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.doctor.id === id) {//clean form if the doctor to be deleted is shown there.
            reset();
        }
        deleteDoctor(id);
    }


    function reset(){
        self.doctor={id:null,name:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
