import axios from 'axios'

export default () => {
    return axios.create({
        baseURL: 'http://localhost:8000',
       
        headers: {
        
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "POST,GET,OPTIONS, PUT, DELETE",
        "Access-Control-Allow-Headers": "Accept, Content-Type, Content-Length, Accept-Encoding, X-CSRF-Token, Authorization",
        'content-type': 'application/json'
    }
       
    })
}