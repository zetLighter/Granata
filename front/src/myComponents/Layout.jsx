import React from 'react'
import {useState} from 'react';
import {Header} from './Header.jsx'
import {Modal} from './Modal.jsx'

export const Layout = ({children}) => {
    const [isModal, setIsModal] = useState(false);

    const openModal = () => {
        setIsModal(true)
    }
    const closeModal = () => {
        setIsModal(false)
    }

    return <React.Fragment>
        <div className='container'>
            <Header openModal={openModal} />
            {children}
            {isModal && <Modal closeModal={closeModal}/>}
        </div>
    </React.Fragment>
}