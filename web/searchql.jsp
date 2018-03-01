<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head>
        <title>UI Elements: Search Box with Filter</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="UI Elements: Search Box with Filter" />
        <meta name="keywords" content="jquery, search box, search input, filter, checkbox "/>
		<link rel="stylesheet" href="CSS/style.css" type="text/css" media="screen"/>
        <style>
            *{
                padding:0;
                margin:0;
            }
            

        </style>
    </head>
    <body>
		<form id="ui_element" action="searchql" class="sb_wrapper">
                    <p>
			<span class="sb_down"></span>
                        <input class="sb_input" name="txttim" type="text"/>
			<input class="sb_search" type="submit" value=""/>
                    </p>
                    <ul class="sb_dropdown" style="display:none;">
                        <li class="sb_filter">Lựa chọn tìm kiếm:</li>
                        <li>
                            <input name="all" value="all" type="checkbox"><label>Tất cả</label></input>
                        </li>
                        <li><label>Loại Nước:
                                <select name="loai" class="loainuoc"> 
                                    <option value="1">Cà Phê</option>
                                    <option value="2">Soda</option>
                                    <option value="5">Yogurt</option>
                                    <option value="3">Chocolate</option>
                                    <option value="4">Trà</option>
                                </select>
                            </label>
                        </li>
                        <li>
                            <label>Giá:</label>
                        </li>
                        <li>
                            <label>Từ: <input class="somin" name="min" value="0" type="number"/></label>
                        </li>
                        <li>
                            
                        </li>
                        <li>
                            <label>Đến: <input class="somax" name="max" value="500000" type="number"/></label>
                        </li>
                        
                    </ul>
                </form>
            
        
        
           
        
		<!-- The JavaScript -->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript">
            $(function() {
				/**
				* the element
				*/
				var $ui 		= $('#ui_element');
				
				/**
				* on focus and on click display the dropdown, 
				* and change the arrow image
				*/
				$ui.find('.sb_input').bind('focus click',function(){
					$ui.find('.sb_down')
					   .addClass('sb_up')
					   .removeClass('sb_down')
					   .andSelf()
					   .find('.sb_dropdown')
					   .show();
				});
				
				/**
				* on mouse leave hide the dropdown, 
				* and change the arrow image
				*/
				$ui.bind('mouseleave',function(){
					$ui.find('.sb_up')
					   .addClass('sb_down')
					   .removeClass('sb_up')
					   .andSelf()
					   .find('.sb_dropdown')
					   .hide();
				});
				
				/**
				* selecting all checkboxes
				*/
				$ui.find('.sb_dropdown').find('label[for="all"]').prev().bind('click',function(){
					$(this).parent().siblings().find(':checkbox').attr('checked',this.checked).attr('disabled',this.checked);
				});
            });
        </script>	
    </body>
</html>