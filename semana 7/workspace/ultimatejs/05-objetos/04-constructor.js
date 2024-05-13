function Usuario(){
    this.id = 1;
    this.recuperarClave = function (){
        console.log('recuperar clave');
    }
}
let usuario = new Usuario();
console.log(usuario);