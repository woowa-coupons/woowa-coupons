export type Item = {
    id: number,
    title: string,
    selected: boolean,
}

export type Coupon = {
    id: number,
    title: string,
    type: string,
    discount: number,
    quantity: number,
}