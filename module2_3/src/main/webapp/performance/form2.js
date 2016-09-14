/**
 * 
 */

function busy(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}        
//monitor client activity
MyListener = zk.$extends(zk.Object, {
	onResponse: function (ctrl) {
		busy(2000);
	}        			
});
var listener = new MyListener();
zWatch.listen({
	onResponse: listener,
});        