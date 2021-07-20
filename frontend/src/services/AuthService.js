import Api from './Api'

export default {
    register(email,password,name) {
        return Api().post('/register/'+ email + '/' + password + '/' + name);
    },

    logout(){

        return Api().post('/logout');
    },

    login(email, password){


        return Api().get('/login/'+email+'/'+password);
    },

    isUserLogedIn(){

        return Api().get('/isUserLogedIn');
    },

    
    isEmployeeLogedIn(){

        return Api().get('/isEmployeeLogedIn');    
    }

    
}
