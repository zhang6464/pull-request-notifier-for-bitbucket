define('plugin/prnfb/pr-triggerbutton', [
  'jquery',
  'aui',
  'bitbucket/util/state'
], function($, AJS, pageState) {
  
  var getResourceUrl = function() {
    return AJS.contextPath() + '/rest/prnfb-admin/1.0/manual/?repositoryId=' + pageState.getRepository().id + '&pullRequestId=' + pageState.getPullRequest().id;
  };

  var waiting = '<span class="aui-icon aui-icon-wait">Wait</span>';

  var $buttonArea = $(".triggerManualNotification").parent();
  var $buttonTemplate = $(".triggerManualNotification");
  $buttonTemplate.empty().remove();
  
  function loadSettingsAndShowButtons() {
   $.get(getResourceUrl(), function(settings) {
  	settings.forEach(function(item) {
  	  var $button = $buttonTemplate.clone();
  	  $button.html(item.title);
  	  $button.click(function() {
        var $this = $(this);
        var text = $this.text();

        $this.attr("disabled", "disabled").html(waiting + " " + text);

        $.post(getResourceUrl()+'&formIdentifier='+item.formIdentifier, function() {
          setTimeout(function() {  
              $this.removeAttr("disabled").text(text);
          }, 500);
        });
        
        return false;
      });
      
      $buttonArea.append($button);
  	});
   });
  }
  
  loadSettingsAndShowButtons();
  
  //If a reviewer approves the PR, then a button may become visible
  $('.aui-button.approve').click(function () {
   setTimeout(function(){
    $(".triggerManualNotification").remove();
    loadSettingsAndShowButtons();
   }, 1000);
  });
});

AJS.$(document).ready(function() {
    require('plugin/prnfb/pr-triggerbutton');
});
