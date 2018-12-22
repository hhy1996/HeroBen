if (window.Notification) {
	var popNotice = function(bodyp, iconp, url) {
		if (Notification.permission == "granted") {
			var notification = new Notification("收到新消息", {
				body : bodyp,
				icon : iconp
			});

			notification.onclick = function() {
				window.open (url,'newwindow','height=500,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
				notification.close();
			};
		}
	};
} else {
}


		var url = "ws://localhost:8080/HeroBen/websocket";
		var ws = "";
		var message = {
			"id" : "",
			"msg" : "",
			"form" : "",
			"to" : ""
		};
		function connection(userid) {
			ws = new WebSocket(url);
			console.log("connection.......");
			ws.onmessage = function(e) {
				var json = eval('(' + e.data.toString() + ')');
				if (json.from != "系统") {
					if (Notification.permission == "granted") {
						popNotice(json.msg, "images/messageicon.jpg", "Chat.jsp?chatwith="+ json.from);
					} else if (Notification.permission != "denied") {
						Notification.requestPermission(function(permission) {
							popNotice(json.msg, "images/messageicon.jpg", "Chat.jsp?chatwith="+ json.from);
						});
					}
				}
			}
			ws.onclose = function() {
			}
			ws.onerror = function(e) {
			}
			ws.onopen = function() {
				message.id =userid;
				message.to = "";
				message.msg = "newUser";
				console.log(JSON.stringify(message));
				ws.send(JSON.stringify(message));
				message.msg = "";
			}
		}
		//connection();