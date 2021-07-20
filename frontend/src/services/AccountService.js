import Api from './Api'

export default {

    getUserOrders() {
        return Api().get('/allOrderItems');
    },

}
