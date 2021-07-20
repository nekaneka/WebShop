import Api from './Api'

export default {

    getCategoryData(rangeFrom, rangeTo) {
        return Api().get('/categoryReport/'+rangeFrom + '/' + rangeTo);
    },

    getProductData(rangeFrom, rangeTo) {
        return Api().get('/productReport/'+rangeFrom + '/' + rangeTo);
    }
}
