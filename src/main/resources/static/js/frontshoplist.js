$(function () {
    //加载商铺列表
    var parentId = $('#parentId').val();
    $.getJSON('/app/shopCategory/getByParentId?parentId=' + parentId, function (res) {
        if (res.status == 200) {
            var data = res.data;
            var contHtml = '<a href="#" class="button" data-category-id=""> 全部类别  </a>';
            $.each(data, function (index, obj) {
                var html = '<a href="#" class="button" data-category-id='
                    + obj.shopCategoryId
                    + '>'
                    + obj.shopCategoryName
                    + '</a>';
                contHtml = contHtml + html;
            });
            $('#shoplist-search-div').html(contHtml);
        }
    })
    //加载区域列表
    $.getJSON('/app/area/getAllArea', function (res) {
        if (res.status == 200) {
            var data = res.data;
            var contHtml = '<option value="">全部街道</option>';
            $.each(data, function (index, obj) {
                var html = '<option value="'
                    + obj.areaId + '">'
                    + obj.areaName + '</option>';
                contHtml = contHtml + html;
            })

            $('#area-search').html(contHtml);
        }
    })


})