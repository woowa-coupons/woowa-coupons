import SideBar from "@components/common/sideBar/SideBar";
import { mainSectionDiv, topLevelDiv } from "./CouponGroup.style";
import CouponGroupCard from "@components/coupon/CouponGroupCard";

export const CouponGroupRegistrationPage = () => (
    <>
        <div css={topLevelDiv}>
            <SideBar></SideBar>
            <div css={mainSectionDiv}>
                <CouponGroupCard></CouponGroupCard>
            </div>
        </div>
    </>
)