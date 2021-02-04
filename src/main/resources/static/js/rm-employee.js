/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var table;

$('document').ready(() => {
    getAll();
    $('#inputSearch').on("keypress", function () {
        let value = $(this).val().toLowerCase();
        $('#dataList div').filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
});

function getAll() {
    let list = document.getElementById('dataList');
    list.innerHTML = "";
    $.ajax({
        url: "/rm-employee/all",
        type: 'GET',
        success: function (data, textStatus, jqXHR) {
            for (var item in data) {
                let e = document.createElement('div');
                e.innerHTML = `
                    <div class="col-xl-12 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-80 py-2">
                            <div class="card-body" >
                                <table width="250px">
                                    <tbody>
                                      <tr>
                                        <td rowspan="3" width="100px" align="left"><img src="https://tmssl.akamaized.net/images/portrait/originals/88103-1540568385.jpg" alt="" title="" height="100px" width="75px"></td>
                                        <td text="">${data[item].name}</td>
                                      </tr>
                                      <tr>
                                        <td th:text="">${data[item].dateBirth}</td>
                                      </tr>
                                      <tr>
                                        <td th:text="">${data[item].nation}</td>
                                      </tr>
                                      <tr>
                                        <td colspan="2" align="center" th:height="50px">
                                             <a class="btn btn-primary btn-block" href="rm-employee/${data[item].id}" ><i class="fas fa-eye"></i>Detail</a>
                                        </td>
                                      </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                `;
                list.appendChild(e);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            let e = document.createElement('div');
            e.innerHTML = `
                    <h2>Failed</h2>
                `;
            list.appendChild(e);
        }
    });
}

