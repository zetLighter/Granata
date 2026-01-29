import {useState} from 'react'; 
import axios from 'axios';

export const OrderModal = ({productId, closeOrderModal}) => {

    const [place, setPlace] = useState('')
    const [quantity, setQuantity] = useState(1)

    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleToOrder = async () => {
        if (quantity < 1 || quantity > 16) {
            setErrorMessage("Некорректное количество товаров")
            setIsError(true)
        } else {
            // await axios.post('http://localhost:4932/api/order', {
            await axios.post('/back/api/order', {
                    dishId: productId,
                    quantity: quantity,
                    place: place
                }, {
                    headers: {Authorization: `Bearer ${window.localStorage.getItem('token')}`}
                })
                .then(() => {
                    setPlace('')
                    setQuantity('')
                    window.location.href = '/orders'
                })
                .catch(error => console.log(error))
        } 
    }

    return (
        <div onClick={closeOrderModal} className="modal-wrap">
            <div onClick={(event) => event.stopPropagation()} className="modal-card">
                <h3 className="modal-title">Оформление товара</h3>
                <form className="modal-form">
                    <label className="modal-label">Адрес:</label>
                    <input onChange={(event) => setPlace(event.target.value)} value={place} className="modal-input"/>
                    <label className="modal-label">Количество:</label>
                    <input type="number" onChange={(event) => setQuantity(event.target.value)} value={quantity} className="modal-input"/>
                    <button type="button" onClick={() => handleToOrder()} className="modal-button">Оформить</button>
                    {isError && <p className='modal-error'>{errorMessage}</p>}
                </form>
            </div>
        </div>
    )
}