package elfinder

class ElfinderTagLib {

  def elfinder = { attrs, body ->
    //out << g.javascript(library: "jquery", plugin: "jquery")
    out << g.javascript(src: "elfinder/jquery-1.6.1.min.js", plugin: "elfinder")
    out << g.javascript(src: "elfinder/jquery-ui-1.8.13.custom.min.js", plugin: "elfinder")

    out << g.javascript(src: "elfinder/elfinder.full.js", plugin: "elfinder")
    out << """<link rel="stylesheet" href="${resource(dir: 'css/elfinder', file: 'elfinder.css', plugin: 'elfinder')}" type="text/css"/>"""
    out << """<link rel="stylesheet" href="${resource(dir: 'css/elfinder/smoothness', file: 'jquery-ui-1.8.13.custom.css', plugin: 'elfinder')}" type="text/css"/>"""

    if (attrs.lang && attrs.lang != "en") {
      out << g.javascript(src: "elfinder/i18n/elfinder.${attrs.lang}.js")
    }

    out << """<div id="elfinder">Loading file browser...</div>"""

    if (attrs.ckeditor) {
      out << """<script type="text/javascript">
    // Helper function to get parameters from the query string.
    function getUrlParam(paramName) {
        var reParam = new RegExp('(?:[\\?&]|&amp;)' + paramName + '=([^&]+)', 'i') ;
        var match = window.location.search.match(reParam) ;

        return (match && match.length > 1) ? match[1] : '' ;
    }

    \$().ready(function() {
        var funcNum = getUrlParam('CKEditorFuncNum');

        var elf = \$('#elfinder').elfinder({
            url: '${g.createLink(controller: "elfinder", action: "connector")}',
            lang : '${attrs.lang ? attrs.lang : 'en'}',
            editorCallback : function(file) {
                window.opener.CKEDITOR.tools.callFunction(funcNum, file);
                window.close();
            },
            resizable: false
        }).elfinder('instance');
    });
</script>"""
    }
    else {
      out << """<script type="text/javascript">
\$().ready(function() {

  var f = \$('#elfinder').elfinder({
    url: '${g.createLink(controller: "elfinder", action: "connector")}',
    lang : '${attrs.lang ? attrs.lang : 'en'}',
    docked : false
  });
});
</script>"""
    }
  }
}
