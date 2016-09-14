zk.afterMount(function() {
	var binder = zkbind.$('$root');
	var $slider = jq('#myslider');
	$slider.on('change', function(){
		//TODO invoke the command
	});
});