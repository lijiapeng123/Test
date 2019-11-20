function  d() {
    $.ajax({
        type:"get",
        url:"localhost:89/test/createExcel",
        data:{},
        dataType:"json",
        success:function(data){
            alert("33")
        }
    });
}