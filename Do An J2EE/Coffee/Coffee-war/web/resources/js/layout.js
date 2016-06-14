/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var URL = window.location.href.split('?')[0];

// Sidebar
$(document).ready(function () {
    // TODO: This is some kind of easy fix, maybe we can improve this
    var setContentHeight = function () {
        // reset height
        $('.right_col').css('min-height', $(window).height());

        var bodyHeight = $('body').height(),
            leftColHeight = $('.left_col').eq(1).height() + $('.sidebar-footer').height(),
            contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;

        // normalize content
        contentHeight -= $('.nav_menu').height() + $('#footer').height();

        $('.right_col').css('min-height', contentHeight);
    };

    $('#sidebar-menu').find('a').on('click', function (ev) {
        var $li = $(this).parent();

        if ($li.is('.active')) {
            $li.removeClass('active');
            $('ul:first', $li).slideUp(function () {
                setContentHeight();
            });
        } else {
            // prevent closing menu if we are on child menu
            if (!$li.parent().is('.child_menu')) {
                $('#sidebar-menu').find('li').removeClass('active');
                $('#sidebar-menu').find('li ul').slideUp();
            }

            $li.addClass('active');

            $('ul:first', $li).slideDown(function () {
                setContentHeight();
            });
        }
    });

    // toggle small or large menu
    $('#menu_toggle').on('click', function () {
        if ($('body').hasClass('nav-md')) {
            $('body').removeClass('nav-md').addClass('nav-sm');
            $('.left_col').removeClass('scroll-view').removeAttr('style');

            if ($('#sidebar-menu').find('li').hasClass('active')) {
                $('#sidebar-menu').find('li.active').addClass('active-sm').removeClass('active');
            }
        } else {
            $('body').removeClass('nav-sm').addClass('nav-md');

            if ($('#sidebar-menu').find('li').hasClass('active-sm')) {
                $('#sidebar-menu').find('li.active-sm').addClass('active').removeClass('active-sm');
            }
        }

        setContentHeight();
    });

    // check active menu
    $('#sidebar-menu').find('a[href="' + URL + '"]').parent('li').addClass('current-page');

    $('#sidebar-menu').find('a').filter(function () {
        return this.href == URL;
    }).parent('li').addClass('current-page').parents('ul').slideDown(function () {
        setContentHeight();
    }).parent().addClass('active');
});
// /Sidebar

// Panel toolbox
$(document).ready(function () {
    $('.collapse-link').on('click', function () {
        var $BOX_PANEL = $(this).closest('.x_panel'),
            $ICON = $(this).find('i'),
            $BOX_CONTENT = $BOX_PANEL.find('.x_content');

        // fix for some div with hardcoded fix class
        if ($BOX_PANEL.attr('style')) {
            $BOX_CONTENT.slideToggle(200, function () {
                $BOX_PANEL.removeAttr('style');
            });
        } else {
            $BOX_CONTENT.slideToggle(200);
            $BOX_PANEL.css('height', 'auto');
        }

        $ICON.toggleClass('fa-chevron-up fa-chevron-down');
    });

    $('.close-link').click(function () {
        var $BOX_PANEL = $(this).closest('.x_panel');

        $BOX_PANEL.remove();
    });
});
// /Panel toolbox

function countChecked() {
    if (checkState === 'all') {
        $(".bulk_action input[name='table_records']").iCheck('check');
    }
    if (checkState === 'none') {
        $(".bulk_action input[name='table_records']").iCheck('uncheck');
    }

    var checkCount = $(".bulk_action input[name='table_records']:checked").length;

    if (checkCount) {
        $('.column-title').hide();
        $('.bulk-actions').show();
        $('.action-cnt').html(checkCount + ' Records Selected');
    } else {
        $('.column-title').show();
        $('.bulk-actions').hide();
    }
}
function addProduct(){ 
     window.open('./faces/AddProduct.xhtml','mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235,menubar=no');
     return true;
 }
 function updateProduct(){ 
     window.open('./faces/UpdateProduct.xhtml','mywindow', 'resizable=no,toolbar=no,scrollbars=yes,height=450,width=530,top=145,left=235,menubar=no');
     return true;
 }
 function refreshAndClose() {
        window.opener.location.reload(true);
        window.close();
    }
    function printDiv(divName) {
     var printContents = document.getElementById(divName).innerHTML;
     var originalContents = document.body.innerHTML;

     document.body.innerHTML = printContents;

     window.print();

     document.body.innerHTML = originalContents;
}