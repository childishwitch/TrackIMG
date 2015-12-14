angular.module('starter.controllers', ['ionic', 'ng-mfb'])
.factory('sharedService', function ($rootScope, $window) {
        var property = [{text:"臺灣",percent:50},{text:"煙火",percent:40},{text:"服務預設",percent:30},{text:"台北市",percent:20}];
		 $window.rootScopes = $window.rootScopes || [];
		$window.rootScopes.push($rootScope);
		return{
			getProperty: function () {
                return property;
            },
            setProperty: function(value) {
                property = value;
				angular.forEach($window.rootScopes, function(scope) {
					if(!scope.$$phase) {
						scope.$apply();
					}
				});
            }
		}
})
.controller('IndexCtrl', function($scope, $ionicPlatform, sharedService) {
/*
 $scope.leftButtons = [{
    type: 'button-icon button-clear ion-navicon',
    tap: function(e) {
      $ionicSideMenuDelegate.toggleLeft($scope.$$childHead);
    }
  }];*/

  	  var updateOrientation=function(){
			if(window.orientation == 0){
				showFullScreen = false;
				showStatusBar = true;
				ionic.Platform.fullScreen(showFullScreen,showStatusBar);
				parent.location = "#/event/port";
			}
			else if(window.orientation == 90 || window.orientation == -90){
				showFullScreen = true;
				showStatusBar = false;
				parent.location = "#/event/land";
				ionic.Platform.fullScreen(showFullScreen,showStatusBar);
			}
			//window.location.reload();
               /*var body=document.body;  
               var viewport=document.getElementById("viewport");  
               if(body.getAttribute("orient")=="landscape"){  
                   body.setAttribute("orient","portrait");  
               }else{  
                   body.setAttribute("orient","landscape");  
               }*/  
   };
		
  var init=function(){  
      updateOrientation();  
      window.addEventListener("orientationchange",updateOrientation,false);  
  };
  window.addEventListener("DOMContentLoaded",init,false);
  //$scope.$on('pause', mfbDeregister);
  /**
  var jsonString = '{"items": [{"value": 4.567401619860497, "text": "\u82f1\u96c4"}, {"value": 3.9425907538571296, "text": "\u904a\u6232"}, {"value": 3.3218091146784494, "text": "\u5b98\u65b9"}, {"value": 2.4552017601000378, "text": "\u653b\u7565"}, {"value": 2.0474363460659375, "text": "\u66f4\u65b0"}, {"value": 1.9091345934520476, "text": "\u806f\u76df"}, {"value": 1.836866560249032, "text": "\u4e2d\u5fc3"}, {"value": 1.5869972927321079, "text": "\u65b0\u805e"}, {"value": 1.4278583209634514, "text": "\u5be6\u6cc1"}, {"value": 1.3085430613891669, "text": "\u96fb\u7af6"}]}';
  $scope.sysItems = (JSON.parse(jsonString)).items;
  **/
  //$scope.sysItems = $scope.sysItems.items;
  //alert("items: "+$scope.sysItems);
  //$scope.myItems = [{text:"台北"},{text:"101大樓"}];
  //$scope.sysItems = [{text:"臺灣",percent:50},{text:"煙火",percent:40},{text:"跨年",percent:30},{text:"台北市",percent:20}];
  //$scope.sysItems = sharedService.getProperty();
  
  $scope.sysItems = function(){return sharedService.getProperty();}
  /*
  $scope.$watch(sharedService.getProperty(), function(newValue,oldValue) {
	alert("new:"+newValue+"old:"+oldValue);
	if(newValue != undefined){
		alert("not equal");
        $scope.sysItems = sharedService.getProperty();
	}
  },true);*/
})

.controller('LandCtrl', function($scope) {
 
})

.controller('PortCtrl', function($scope) {
		
		/**click the item on hold*/
		$scope.onHold = function(item){
		if(!item)
			return;
		item.checked = !(item.checked);
		};
		$scope.onClick = function(item){//will be fired on "ionic serve" when on-Hold
			if(!item)
				return;
			top.name=item.text;
			location="search/search.html";
		};
})

.controller('EventMenuCtrl', function($scope) {

})

.controller('StarCtrl', function($scope,$ionicPlatform,$ionicPopup) {
	$scope.omfbClose = function(){omfbClose();};
			 var showConfirm = function() {
			   var confirmPopup = $ionicPopup.confirm({
				 title: '閱讀下張圖片',
				 template: '卻定要返回相機,讀取下張圖片?'
			   });
			   confirmPopup.then(function(res) {
				 if(res) {
					/**close the webview*/
					cordova.exec(function(param) {
						alert("send success:"+param);
					}, function(param){
						alert("send fails"+param);
					}, "Device",
					"webViewMsg", ["close"]);
				 } else {
				   console.log('not sure to close webview');
				 }
			   });
			 };
	/**when click on the back button on the hardware*/
	var myBackDeregister = $ionicPlatform.registerBackButtonAction(
	function () {
		var omfb = document.getElementsByClassName('orient-mfb')[0];
		if(omfb.getAttribute("data-mfb-state") == 'open')
			omfbClose();
		else{
			showConfirm();
			}
		}, 101
	);
    //$scope.myData = [10,20,30,40,60, 80, 20, 50];
});