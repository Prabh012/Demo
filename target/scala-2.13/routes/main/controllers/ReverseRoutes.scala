// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:10
  class ReverseLoginController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def listOfStudent(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "listOfStudent")
    }
  
    // @LINE:12
    def save(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "save")
    }
  
    // @LINE:13
    def updateStatus(id:Integer, statusId:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "update/status/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("statusId", statusId)))
    }
  
    // @LINE:10
    def create(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "create")
    }
  
    // @LINE:11
    def fetch(id:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "fetch/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:14
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
