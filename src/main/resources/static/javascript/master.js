function startup(){
    updateValues();
}

function updateValues(){
updateColumns()
updateRows()
}

function updateColumns(){
    $("#cols").html($("#columns_range").val())
}

function updateRows(){
    $("#rows").html($("#rows_range").val())
}