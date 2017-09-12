'use strict';

angular.module('myApp').controller('PatientController', ['$scope', 'PatientService', function($scope, PatientService) {
    var self = this;
    self.patient={id:null,name:''};
    self.patients=[];

    self.submit = submit;
    /*self.edit = edit;
    self.remove = remove;
    self.reset = reset;*/


    fetchAllPatients();

    function fetchAllPatients(){
        PatientService.fetchAllPatients()
            .then(
            function(d) {
                self.patients = d;
            },
            function(errResponse){
                console.error('Error while fetching Patients');
            }
        );
    }

   function createPatient(patient){
        PatientService.createPatient(patient)
            .then(
            fetchAllPatients,
            function(errResponse){
                console.error('Error while creating Patient');
            }
        );
    }

    function updatePatient(patient, id){
        PatientService.updatePatient(patient, id)
            .then(
            fetchAllPatients,
            function(errResponse){
                console.error('Error while updating Patient');
            }
        );
    }

   /* function deletePatient(id){
        PatientService.deletePatient(id)
            .then(
            fetchAllPatients,
            function(errResponse){
                console.error('Error while deleting Patient');
            }
        );
    }*/

    function submit() {
        if(self.patient.id===null){
            console.log('Saving New Patient', self.patient);
            createPatient(self.patient);
        }else{
            updatePatient(self.patient, self.patient.id);
            console.log('Patient updated with id ', self.patient.id);
        }
        reset();
    }

   /* function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.patients.length; i++){
            if(self.patients[i].id === id) {
                self.patient = angular.copy(self.patients[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.patient.id === id) {//clean form if the patient to be deleted is shown there.
            reset();
        }
        deletePatient(id);
    }


    function reset(){
        self.patient={id:null,name:''};
        $scope.myForm.$setPristine(); //reset Form
    }*/

}]);
