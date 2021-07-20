import Api from './Api'

export default {

    getProducts() {
        return Api().get('/items/all')
    }
}
