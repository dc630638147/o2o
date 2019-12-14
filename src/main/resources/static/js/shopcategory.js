$(function () {

    var getshopCategoryUrl = '/productCategoryApi/getProductCatetgroyList';
    var shopId = $('#shopId').val();

    $.ajax({
        type:'POST',
        data:JSON.stringify({"shopId":shopId}),
        url:getshopCategoryUrl,
        dataType: "json",  // 服务器响应的数据类型
        contentType: "application/json;charset=utf-8",
        success:function (res) {
            if(res.status == 200){
                var data = res.data
                //遍历
                var conHtml = '';
                $.each(data,function (index,obj) {
                    var html = '<div class="row row-product-category">'+
                        '<div class="col-40 ">'+obj.productCategoryName+'</div>'+
                        '<div class="col-40">'+obj.priority+'</div>'+
                        '<div class="col-20">'+'<a href="javascript:void(0);" onclick="deletePc('+obj.productCategoryId+')">删除</a>'+'</div>'+
                        '</div>';
                    conHtml = conHtml+html;
                })
                $("#shopWrap").html(conHtml);
            }else {
                alert(res.msg);
            }
        }});

    //新增
    $("#add").click(function () {
        var shopWrap = $("#shopWrap")
        var conHtml = shopWrap.html();
        var html = '<div class="row row-product-category temp">'+
            '<div class="col-40 "><input class="category-input  category"/></div>'+
            '<div class="col-40"><input class="category-input  priority"/></div>'+
            '</div>';
        conHtml = conHtml+html;
        shopWrap.html(conHtml);
    });
    
    $('#submit').click(function () {
        var inpArr= $(".temp");
        var list = [];
        inpArr.map(function (index,obj) {
            var name =$(obj).find('.category').val();
            var priority = $(obj).find('.priority').val();
           var tem = {}
            tem.productCategoryName = name;
            tem.priority = priority;
            tem.shopId = shopId;
            list.push(tem);
        })

        $.ajax({
            type:'post',
            url:'/productCategoryApi/batchAdd',
            data:JSON.stringify(list),
            contentType: "application/json;charset=utf-8",
            success:function (res) {
                if(res.status == 200){
                    window.location.reload();
                    alert("上传成功");
                }else{
                    alert(res.msg);
                }
            }
        })


    })
})

function  deletePc(id) {
    $.ajax({
        type:'POST',
        url:'/productCategoryApi/delete',
        data:{"productCategoryId":id},
        success:function (res) {
            if(res.status == 200){
                window.location.reload();
                alert("删除成功");
            }else{
                alert(res.msg);
            }
        },
    })
}