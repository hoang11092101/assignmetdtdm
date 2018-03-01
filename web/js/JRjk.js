function layid(ten)
	{
		return document.getElementById(ten);
	}

function layclass(ten)
	{
		return documen.getElementByClassName(ten);
	}


function layname(ten,stt)
	{
		return document.getElementsByName(ten)[stt];
	}
   
 function checksign(){
                        var cc = getCookie("loaitk");
                    if(cc == "loaitk = user"){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                    }else if(cc == "loaitk = admin" ){
                        document.getElementById("sign").innerHTML = "Đăng xuất";
                        document.getElementById("sign").href ="asign.jsp";
                    }
                    else{
                        document.getElementById("sign").innerHTML = "Đăng nhập";
                        document.getElementById("sign").href ="login.jsp";
                    }
                    alert(cc);
                    }
                    
function getCookie(cname) {
                        var name = cname + "=";
                        var ca = document.cookie.split(';');
                        for(var i = 0; i < ca.length; i++) {
                        var c = ca[i];
                        while (c.charAt(0) == ' ') {
                        c = c.substring(1);
                    }
                    if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
                    }
                    }
                return "";
                }