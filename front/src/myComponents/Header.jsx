import {useState, useEffect} from 'react'
import img from '../static/granate-logo.jpg'
import {NavLink} from 'react-router-dom'
import axios from 'axios'

const activeStyle = {
    color: 'gray'
}

export const Header = () => {
    const [isAuth, setIsAuth] = useState(false);

    useEffect(() => {
        const isValid = async () => {
            if(window.localStorage.getItem('token') != null) {
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
        isValid()
        
    }, [])

    const exitHandle = () => {
        window.localStorage.removeItem('token');
        window.location.reload()
    }

    return (
        <div className='header'>
            <NavLink to="/" className='logo'>
                <img className="logo-img" src={img}/>
                <p className='logo-title'>ГРАНАТА</p>
            </NavLink>
            { isAuth
              ?  <div className='header-menu'> 
                        <NavLink style={({isActive}) => isActive ? activeStyle : undefined} to="/orders" className='header-ref'>Заказы</NavLink>
                        <p onClick={exitHandle} className='header-ref'>Выйти</p>
                </div>
              :  <div className='header-menu'> 
                        <NavLink style={({isActive}) => isActive ? activeStyle : undefined} to="/auth" className='header-ref'>Войти</NavLink>
                </div>
            }
        </div>
    )
}
