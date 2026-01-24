import {useState} from 'react';
import axios from 'axios'

export const Auth = () => {
    const [isLogin, setIsLogin] = useState(true)
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [email, setEmail] = useState('')

    const [isError, setIsError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('Ошибка') 

    const handleLogin = async () => {
        const response = await axios.post('/back/auth/login', {
                username: username,
                password: password
            },
	    {withCredentials: true})
            .catch(error => {
                if(error.response != undefined) {
                    setErrorMessage(error.response.data.message)
                } else {
                    setErrorMessage('Ошибка запроса')
                }
                console.log(error)
            })

            if(response) {
                setUsername('')
                setPassword('')

                setIsError(false)

                window.localStorage.setItem('token', response.data.token)
                window.location.href = '/'
            } else {
                setIsError(true)
            }  
    }
    const handleRegister = async () => {
        if(username.length < 3 || password.length < 6 || !email.includes('@') || email.length < 8) {
            setIsError(true)
            setErrorMessage('Неккоректные данные')
        } else {
            const response = await axios.post('/back/auth/registration', {
                    username: username,
                    password: password,
                    confirmPassword: confirmPassword,
                    email: email
                },
            {withCredentials: true}
            ).catch(error => {
                if(error.response != undefined) {
                    setErrorMessage(error.response.data.message)
                } else {
                    setErrorMessage('Ошибка запроса')
                }
                console.log(error)
            })

            if(response) {
                setUsername('')
                setPassword('')
                setConfirmPassword('')
                setEmail('')

                setIsError(false)
                
                setIsLogin(true)
            } else {
                setIsError(true)
            }
        }
    }

    return (
        <div className='auth-wrap'>
            {isLogin
            ? <div className='auth-card'>
                <label className="auth-title">Вход</label>
                <form className="auth-form">
                    <label className='auth-label'>Имя пользователя:</label>
                    <input onChange={(event) => setUsername(event.target.value)} value={username} className='auth-input' placeholder=""/>
                    <label className='auth-label'>Пароль:</label>
                    <input onChange={(event) => setPassword(event.target.value)} value={password} className='auth-input'/>
                    <button onClick={handleLogin} type='button' className="auth-button">Войти</button>
                </form>
                {isError && <p className='auth-error'>{errorMessage}</p>}
                <p onClick={() => setIsLogin(false)} className="change-auth">Регистрация</p>
            </div>
            : <div className='auth-card'>
                <label className="auth-title">Регистрация</label>
                <form className="auth-form">
                    <label className='auth-label'>Имя пользователя:</label>
                    <input onChange={(event) => setUsername(event.target.value)} value={username} className='auth-input'/>
                    <label className='auth-label'>Почта:</label>
                    <input onChange={(event) => setEmail(event.target.value)} value={email} className='auth-input'/>
                    <label className='auth-label'>Пароль:</label>
                    <input onChange={(event) => setPassword(event.target.value)} value={password} className='auth-input'/>
                    <label className='auth-label'>Подтверждение пароля:</label>
                    <input onChange={(event) => setConfirmPassword(event.target.value)} value={confirmPassword} className='auth-input'/>
                    <button onClick={handleRegister} type='button' className="auth-button">Зарегестрироваться</button>
                </form>
                {isError && <p className='auth-error'>{errorMessage}</p>}
                <p onClick={() => setIsLogin(true)} className="change-auth">Вход</p>
            </div>}
        </div>
    )
}
