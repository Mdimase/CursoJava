// Call the dataTables jQuery plugin
$(document).ready(function() {  //ejecuta una vez cargada la pagina
    cargarUsuarios();
  $('#usuarios').DataTable();
  document.getElementById("txtEmailUsuario").outerHTML = localStorage.email;
});

function getHeaders(){
    return {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization':localStorage.token
                };
}

async function cargarUsuarios(){
    const request = await fetch('usuarios', {
        method: 'GET',
        headers: getHeaders()
      });
    const usuarios = await request.json();

    listadoHtml = '';
    for (let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        if(usuario.telefono == null){
            usuario.telefono = '-';
        }
        let usuarioHtml = '<tr><td>'+usuario.id+'</td><td>'
        + usuario.nombre + ' '
        + usuario.apellido + '</td><td>'
        + usuario.email +'</td><td>'
        + usuario.telefono
        + '</td><td>' + botonEliminar + '</td></tr>';
        listadoHtml += usuarioHtml;
    }

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

async function eliminarUsuario(id){

    // click en no
    if(!confirm('Desea eliminar este usuario?')){
        return;
    }

    const request = await fetch('usuarios/' + id, {
            method: 'DELETE',
            headers: getHeaders()
          });

    // lo ideal seria que borre la fila sin recargar la pagina
    location.reload();
}
