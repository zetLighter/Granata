import {useState} from 'react'
import axios from 'axios'


export const AdditionModal = ({closeModal}) => {
    const [title, setTitle] = useState('')
    const [price, setPrice] = useState('')
    const [img, setImg] = useState('')

    const [isError, setIsError] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')


    const handleAddition = async () => {
        console.log(title, price, img)
        const response = await axios.post('/back/api/admin/product', {
        // const response = await axios.post('http://localhost:4932/api/admin/product', {
            title: title,
            price: price,
            img: img
        }, {
            headers: {
                Authorization: `Bearer ${window.localStorage.getItem('token')}` 
            }
        },
        {withCredentials: true}
        ).then(() => {
                setTitle('')
                setPrice('')
                setImg('')
                window.location.href = '/'
            }
        )
        .catch(error => {
            console.log(error)
        })
    }

    return (
        <div onClick={closeModal} className="modal-wrap">
            <div onClick={(event) => event.stopPropagation()} className="modal-card">
                <h3 className="modal-title">Добавление товара</h3>
                <form className="modal-form">
                    <label className="modal-label">Название:</label>
                    <input onChange={(event) => setTitle(event.target.value)} value={title} className="modal-input"/>
                    <label className="modal-label">Стоимость:</label>
                    <input onChange={(event) => setPrice(event.target.value)} value={price} className="modal-input"/>
                    <label className="modal-label">Ссылка на изображение:</label>
                    <input onChange={(event) => setImg(event.target.value)} value={img} className="modal-input"/>
                    <button type="button" onClick={handleAddition} className="modal-button">Добавить</button>
                    {isError && <p className='modal-error'>{errorMessage}</p>}
                </form>
            </div>
        </div>
    )
}