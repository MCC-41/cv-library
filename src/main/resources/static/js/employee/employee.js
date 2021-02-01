/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var table;

$('document').ready(() => {
    getAll();
});

function btnAdd() {
    $('.btnModalTitle').text('Add');
    setForm('Add', '', '', '', '', '', false, '');
}
function btnEdit(id, name, institution, year, trainingType, file) {
    $('.btnModalTitle').text('Update');
    setForm('Update', id, name, institution, year, trainingType, true, file);
}
function setForm(title, id, name, institution, year, trainingType, isEnable, file) {
    $('.trainingTitle').text(title);
    $("#id").val(id);
    $("#name").val(name);
    $("#institution").val(institution);
    $("#year").val(year);
    $("#trainingType").val(trainingType);
    if(file!==''){
        console.log(file);
//        $("#fileName").val('file');
    }
    
    
    console.log(name);
    console.log(institution);
    console.log(year);
    console.log(trainingType);
}
function getAll() {
    table = $('#trainingTable').DataTable({
        'sAjaxSource': '/training/all',
        'sAjaxDataProp': '',
        'columns': [
            {'data': 'id'},
            {'data': 'name'},
            {'data': 'institution'},
            {'data': 'year'},
            {'data': 'trainingType.name'},
            {'data': 'file'},
            {
                'render': function (data, type, row, meta) {
                    return `
                        <tr>
                            <td>
                                <button class="btn btn-warning" href="#" data-toggle="modal" data-target="#trainingModal" 
                                        id="${row.id}"
                                        name="${row.name}"
                                        institution="${row.institution}"
                                        year="${row.year}"
                                        trainingType="${row.trainingType.id}"
                                        file="${row.file}"
                                        onclick="btnEdit(this.getAttribute('id'),this.getAttribute('name'),this.getAttribute('institution'),this.getAttribute('year'),this.getAttribute('trainingType'),this.getAttribute('file'))"><i class="fa fa-sm fa-edit mx-1"></i></button>
                                <button class="btn btn-danger delete-confirm mx-1" 
                                        id="${row.id}" 
                                        onclick="deleted(this.getAttribute('id'))"><i class="fa fa-sm fa-trash mx-1 "></i></button>
                            </td>
                        </tr>
                    `;
                }
            }
        ]
    });
}
function save() {
    let id = $("#id").val();
    let name = $("#name").val();
    let institution = $("#institution").val();
    let year = $("#year").val();
    let type = $("#trainingType").val();
    let file = $("#fileName").val();
    console.log(id);
    console.log(name);
    console.log(institution);
    console.log(year);
    console.log(type);
    console.log(file);
    var award = {
        "name": name,
        "institution": institution,
        "year": year,
        "trainingType" : {
            "id" : type
        },
        "file" : file
    };
    if (id === "") {
        insert(award);
    } else {
        update(id, award);
    }
}
function update(id, work) {
    $.ajax({
        contentType: 'application/json',
        type: 'PUT',
        url: "/training/" + id,
        data: JSON.stringify(work),
        success: function (data) {
            Swal.fire(
                    'Added!',
                    'Your file has been Updated.',
                    'success'
                    );
            $('#trainingModal').modal('hide');
            table.destroy();
            getAll();
        },
        error: function (data) {
            Swal.fire(
                    'Failed!',
                    'Your file cannot be Updated.',
                    'error'
                    );
        }
    });
}

