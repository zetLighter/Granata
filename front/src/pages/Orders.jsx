import {useState, useEffect} from 'react'
import axios from 'axios'


export const Orders = () => {
    const [orders, setOrders] = useState([])
    const [reload, setReload] = useState(false)

    useEffect(() => {
        
        const getOrders = async () => {
            try {
                // const reqOrders = await axios.get('http://localhost:4932/api/orders', {
                const reqOrders = await axios.get('/back/api/orders', {
                    headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
                }).then(response => response.data)
                .catch(error => console.log(error))

                const promises = reqOrders.map(async (order) => {
                    // await axios.get(`http://localhost:4932/api/product?id=${order.dishId}`, {
                    await axios.get(`/back/api/product?id=${order.dishId}`, {
                        headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
                    }).then(response => {
                        order.title = response.data.title
                        order.price = response.data.price
                        order.img = response.data.img
                    })
                    .catch(error => {
                        console.log(error)})
                    return order
                })
                const upOrders = await Promise.all(promises)

                setOrders(upOrders.reverse())
            } catch(error) {
                window.location.href = '/'
            }
        }

        getOrders()
    }, [reload]) 

    const handleRemove = async (id) => {
        // await axios.delete(`http://localhost:4932/api/order?dishId=${id}`, {
        await axios.delete(`/back/api/order?dishId=${id}`, {
            headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
        })
        setReload(!reload)
    }

    return (
        <div className="catalog-wrap">
            <h2 className="orders-title">Отслеживание</h2>
            <div className="order-container">
                {orders.map(order => (
                     <div key={order.id} className="order-card">
                        <img className="order-img" src={order.img}/>
                        <div className="product-info">
                            <p className="order-title">Товар: {order.title}</p>
                            <p className="order-price">Общая стоимость: {(order.price * order.quantity).toFixed(2)} Руб.</p>
                        </div> 
                        <div className="order-info">
                            <p className="order-status">Статус: {order.status}</p>
                            <p className="order-place">Адрес: {order.place}</p>
                            <p className="order-quantity">Количество: {order.quantity}</p>
                        </div>
                        { order.status === 'Доставлен'
                            ?<button onClick={() => handleRemove(order.dishId)} className="order-btn complete">Завершить</button>
                            :<button onClick={() => handleRemove(order.dishId)} className="order-btn remove">Отменить</button>
                        }
                    </div>
                ))}
            </div>
        </div>
    )
}
