// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:10
  class ReverseLoginController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:15
    def listOfStudent: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.listOfStudent",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "listOfStudent"})
        }
      """
    )
  
    // @LINE:12
    def save: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.save",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "save"})
        }
      """
    )
  
    // @LINE:13
    def updateStatus: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.updateStatus",
      """
        function(id0,statusId1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "update/status/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("statusId", statusId1))})
        }
      """
    )
  
    // @LINE:10
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.create",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "create"})
        }
      """
    )
  
    // @LINE:11
    def fetch: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.fetch",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "fetch/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:14
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }

  // @LINE:9
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
