import {useEffect, useState} from 'react'
import axios from 'axios'

export const Catalog = () => {
    const [products, setProducts] = useState([])
    const [isAuth, setIsAuth] = useState(false)

    useEffect(() => {

        const isValid = async () => {
            if(window.localStorage.getItem('token') != null) {
                // const response = await axios.get('http://localhost:4932/valid',
                const response = await axios.get('/back/valid',
                    {headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}})
                .catch(error => {
                    console.log(error)
                    setIsAuth(false)})
                if(response) {
                    setIsAuth(response.data)
                }
            } else {
                setIsAuth(false)
            }
        }

        const getProducts = async () => {
            // await axios.get('http://localhost:4932/api/products')
            await axios.get('/back/api/products')
                .then(response => setProducts(response.data))
                .catch(error => console.log(error))
        }
        isValid()
        getProducts()
    }, [])

    const handleToOrder = async (productId) => {
        // await axios.post('http://localhost:4932/api/order', {
        await axios.post('/back/api/order', {
                dishId: productId,
                quantity: 1
            }, {
                headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
            })
            .catch(error => console.log(error))
    }

    return (
        <div className="catalog-wrap">
            <h2 className="orders-title">Каталог</h2>
            <div className="card-container">
                {products.map(product => (
                    <div key={product.id} className="card">
                        <img src={product.img} className='card-img'/>
                        <div className="card-info">
                            <p className="card-title">{product.title}</p>
                            <p className="card-price">{product.price} Руб.</p>
                        </div>
                        {isAuth && <button onClick={() => {handleToOrder(product.id)}} className='order-button'>Заказать</button>}
                    </div>
                ))}
            </div>
        </div>
    )
}
