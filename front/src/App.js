import React from 'react'
import Header from './components/Header.js'
import CardContainer from './components/CardContainer.js'
import Registration from './components/Registration.js'
import axios from 'axios'
import Login from './components/Login.js'
import OrderContainer from './components/OrderContainer.js'

class App extends React.Component {
    constructor(props) {
        super(props)
        
        axios.get("http://localhost:4932/api/dishes")
            .then((response) => {
                this.setState({dishes: response.data})
            })
        this.state = {
            isMain: true,
            isFollow: false,
            isExistsAccount: true,
            isAuth: false,
            isWrong: false,
            isValid: true,
            isClean: false,
            dishes: []
        }
        this.createUser = this.createUser.bind(this)
        this.removeUser = this.removeUser.bind(this)
        this.removeDish = this.removeDish.bind(this);
        this.editDish = this.editDish.bind(this);
        this.isLogin = this.isLogin.bind(this)
        this.changeExists = this.changeExists.bind(this);
        this.toAuth = this.toAuth.bind(this);
        this.toMain = this.toMain.bind(this);
        this.toFollow = this.toFollow.bind(this);
        this.removeOrder = this.removeOrder.bind(this);
        this.toOrder = this.toOrder.bind(this);
    }
    render() {
        return (
        <div className="wrap">
            <Header toFollow={this.toFollow} toMain={this.toMain} toAuth={this.toAuth} title="ГРАНАТА"/>
                {this.state.isMain 
                && 
                <div className="catalog">
                    <input className="search-input" onChange={(event) => this.setState({text: event.target.value})} placeholder="Поиск..."/>
                    <CardContainer toOrder={this.toOrder} onEdit={this.editDish} onRemove={this.removeDish} dishes={this.state.dishes}/>
                </div>}
                {this.state.isAuth
                &&
                <div>
                    {this.state.isExistsAccount
                    ? <Login onChange={this.changeExists} isWrong={this.state.isWrong} onLogin={this.isLogin}/>
                    : <Registration isClean={this.state.isClean} onChange={this.changeExists} isValid={this.state.isValid} onCreate={this.createUser}/>
                    }
                </div>}
                {this.state.isFollow
                &&
                <div className="follow-wrap">
                    <OrderContainer removeOrder={this.removeOrder} toFollow={this.toFollow}/>
                </div>
                }
        </div>)
    }
    toOrder(dishId) {
        axios.post("http://localhost:4932/api/order", {
            user_id: 1,
            dish_id: dishId,
            quantity: 1
        })
        this.toFollow()
    }
    removeOrder() {
        return null
    }
    toFollow() {
        this.setState({isFollow: true, isAuth: false, isMain: false})
    }
    toMain() {
        this.setState({isMain: true, isAuth: false, isFolloe: false})
    }
    toAuth() {
        this.setState({isAuth: true, isMain: false, isFollow: false})
    }
    changeExists() {
        this.setState({isExistsAccount: !this.state.isExistsAccount})
    }
    isLogin(data) {
        console.log(data);
        axios.post("http://localhost:4932/auth/login", {
            username: data.login,
            password: data.password
        }).then((response) => {
            if(response.status == 200) {
                this.setState({isAuth: true})
                this.setState({isWrong: false})
                localStorage.setItem("token", response.data.token)
                console.log(localStorage.getItem("token"))
            } else {
                this.setState({isAuth: false})
                this.setState({isWrong: true})
            }
        })


    }
    createUser(user) {
        if (!user.email.includes('@') || !user.username || !user.email || !user.password){
            this.setState({isValid: false})
            this.setState({isClean: false})
        } else {
            axios.post("http://localhost:4932/api/user", {
                username: user.username,
                email: user.email,
                password: user.password
            }).then((response) => console.log(response))
            this.setState({isValid: true})
            this.setState({isClean: true})
        }

        // const id = this.state.users.length + 1;
        // this.setState({users: [...this.state.users, {id, ...user}]})
    }
    removeUser(id) {
        this.setState({
            users: this.state.users.filter((user) => user.id !== id)
        })
    }
    removeDish(id) {
        this.setState({
            dishes: this.state.dishes.filter((dish) => dish.id !== id)
        })
    }
    editDish(dish) {
        console.log(dish)
        axios.put("http://localhost:4932/api/dish", dish)
        window.location.reload();
        // let allDishes = this.state.dishes
        // allDishes[dish.id-1] = dish
        // this.setState({dishes: []}, () => {
        //     this.setState({dishes: [...allDishes]})
        // })
    }
}

export default App