import {useEffect, useState} from 'react';
import axios from 'axios';

export const EmployeePanel = () => {

    const [isAdmin, setIsAdmin] = useState(false); 
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        const getOrders = async () => {
            try {
                // const reqOrders = await axios.get('http://localhost:4932/api/admin/orders', {
                const reqOrders = await axios.get('/back/api/admin/orders', {
                        headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
                    })
                    .then((response) => response.data)
                    .catch((error) => console.log(error))

                const promises = reqOrders.map(async (order) => {
                    // await axios.get(`http://localhost:4932/api/product?id=${order.dishId}`,
                    await axios.get(`/back/api/product?id=${order.dishId}`,
                        {headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}}
                    ).then(response => {
                        order.product = response.data
                    }).catch(error => console.log(error))
                    return order;
                })
                const productOrders = await Promise.all(promises)

                const sPromises = productOrders.map(async (order) => {
                    // await axios.get(`http://localhost:4932/api/admin/user?id=${order.userId}`,
                        await axios.get(`/back/api/admin/user?id=${order.userId}`,
                        {headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}}
                    ).then(response => {
                        order.user = response.data
                    }).catch(error => console.log(error))
                    return order;
                })
                const userProductOrders = await Promise.all(sPromises)
                setOrders(userProductOrders.reverse())
            } catch(error) {
                window.location.href = '/'
            }
        }
        getOrders()
    }, [])

    const changeStatus = async (id, status) => {
        // await axios.put('http://localhost:4932/api/admin/order', {
        await axios.put('/back/api/admin/order', {
            id: id,
            status: status
        },
            {headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}}
        )
        .catch(error => console.log(error))
    }

    return (
        <div className="catalog-wrap">
            <h2 className="orders-title">Панель управления заказами</h2>
            <div className="order-panel-container">
                {orders.map(order => (
                     <div key={order.id} className="order-panel-card">
                        <div className='order-desc'>
                            <img className="order-img" src={order.product.img}/>
                            <div className="product-info">
                                <p className="order-title">Товар: {order.product.title}</p>
                                <p className="order-price">Общая стоимость: {(order.product.price * order.quantity).toFixed(2)} Руб.</p>
                            </div> 
                            <div className="order-info">
                                <p className="order-place">Адрес: {order.place}</p>
                                <p className="order-quantity">Количество: {order.quantity}</p>
                            </div>
                            <div className='user-info'>
                                <p className='user-username'>Пользователь: {order.user.username}</p>
                                <p className='user-email'>Почта: {order.user.email}</p>
                                <p className="order-time">Время заказа: {order.time}</p>
                            </div>
                        </div>
                        <div className='change-status-form'>
                            <p className='order-status-title'>Статус:</p>
                            <select value={order.status} onChange={event => {
                                setOrders(prev => (
                                    prev.map(curOr => curOr.id === order.id ? {...curOr, status: event.target.value} : curOr)
                                ))
                            }} className='status-select'>
                                <option>Обрабатывается</option>
                                <option>Собирается</option>
                                <option>Отправляется</option>
                                <option>Едет</option>
                                <option>Доставлен</option>
                            </select>
                            <button className='change-status-button' onClick={() => changeStatus(order.id, order.status)} type="button">Подтвердить</button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}