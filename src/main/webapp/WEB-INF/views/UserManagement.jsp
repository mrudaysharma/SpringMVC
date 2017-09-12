<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>  
        <title>AngularJS $http Example</title>  
        <style>
            .doctorname.ng-valid {
                background-color: lightgreen;
            }
            .doctorname.ng-dirty.ng-invalid-required {
                background-color: red;
            }
            .doctorname.ng-dirty.ng-invalid-minlength {
                background-color: yellow;
            }

            .email.ng-valid {
                background-color: lightgreen;
            }
            .email.ng-dirty.ng-invalid-required {
                background-color: red;
            }
            .email.ng-dirty.ng-invalid-email {
                background-color: yellow;
            }

        </style>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
    </head>
    <body ng-app="myApp" class="ng-cloak">
        <div class="container">
            <div class="row">
                <div class="col-xs-6">
                    <div class="generic-container" ng-controller="DoctorController as ctrl">

                        <div class="panel panel-default">

                            <div class="panel-heading"><span class="lead">List of Doctors </span></div>
                            <div class="tablecontainer">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID.</th>
                                            <th>Name</th>
                                            <th width="20%"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="u in ctrl.doctors">
                                            <td><span ng-bind="u.id"></span></td>
                                            <td><span ng-bind="u.name"></span></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="generic-container" ng-controller="RoomController as rctrl">

                        <div class="panel panel-default">

                            <div class="panel-heading"><span class="lead">List of Rooms </span></div>
                            <div class="tablecontainer">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID.</th>
                                            <th>Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="r in rctrl.rooms">
                                            <td><span ng-bind="r.id"></span></td>
                                            <td><span ng-bind="r.name"></span></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="generic-container" ng-controller="PatientController as pctrl">
                <div class="panel-heading"><span class="lead">Patient Admission Form </span></div>
                <div class="formcontainer">
                    <form ng-submit="pctrl.submit()" name="myForm" class="form-horizontal">
                        <input type="hidden" ng-model="pctrl.patient.id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="file">Name</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="pctrl.patient.name" name="name" class="username form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                                    <div class="has-error" ng-show="myForm.$dirty">
                                        <span ng-show="myForm.name.$error.required">This is a required field</span>
                                        <span ng-show="myForm.name.$error.minlength">Minimum length required is 3</span>
                                        <span ng-show="myForm.name.$invalid">This field is invalid </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="file">Gender</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="pctrl.patient.gender" name="gender" class="address form-control input-sm" placeholder="Enter your Gender. [This field is validation free]"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="file">DOB</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="pctrl.patient.dob" name="dob" class="email form-control input-sm" placeholder="Enter your DOB [This field is validation free]" />
                                    <!--div class="has-error" ng-show="myForm.$dirty">
                                        <span ng-show="myForm.email.$error.required">This is a required field</span>
                                        <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                    </div-->
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div ng-controller="RoomController as rrctrl">
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable" for="file">Room</label>
                                    <div class="col-md-7">

                                        <select name="roomName" class="email form-control input-sm">
                                            <option ng-repeat="r in rrctrl.rooms">{{r.name}}</option>
                                        </select>
                                        <!--input type="dob" ng-model="pctrl.patient.dob" name="dob" class="email form-control input-sm" placeholder="Enter your DOB [This field is validation free]" /-->
                                        <!--div class="has-error" ng-show="myForm.$dirty">
                                            <span ng-show="myForm.email.$error.required">This is a required field</span>
                                            <span ng-show="myForm.email.$invalid">This field is invalid </span>
                                        </div-->

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
                                <input type="submit"  value="{{!pctrl.patient.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" >
                                <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
        <script src="<c:url value='/static/js/app.js' />"></script>
        <script src="<c:url value='/static/js/service/doctor_service.js' />"></script>
        <script src="<c:url value='/static/js/controller/doctor_controller.js' />"></script>
        <script src="<c:url value='/static/js/service/room_service.js' />"></script>
        <script src="<c:url value='/static/js/controller/room_controller.js' />"></script>
        <script src="<c:url value='/static/js/service/patient_service.js' />"></script>
        <script src="<c:url value='/static/js/controller/patient_controller.js' />"></script>
    </body>
</html>