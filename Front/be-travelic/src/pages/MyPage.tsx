import React from "react";
import { useState } from "react";
import ArticleModal from "../components/common/ArticleModal";
import MyMap from "../components/MyPage/MyMap";
import MyPageCard from "../components/MyPage/MyPageCard";
import PhotoInputModal from "../components/MyPage/PhotoInputModal";
import PlaceContainer from "../components/MyPage/PlaceContainer";

const MyPage = () => {
  const [showModal, setShowModal] = useState<boolean>(false);
  const [showArticleModal, setShowArticleModal] = useState<boolean>(false);

  const openModal = () => {
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
  };

  return (
    <div className="bg-blue-100">
      {showArticleModal && (
        <ArticleModal setShowArticleModal={setShowArticleModal} />
      )}
      {showModal && (
        <PhotoInputModal
          setShowModal={setShowModal}
          setShowArticleModal={setShowArticleModal}
        />
      )}
      {/* 프로필 */}
      <div className="flex justify-around">
        <section>
          <MyPageCard />
        </section>
        <section>
          {/* 맵 */}
          <MyMap setShowModal={setShowModal} />
        </section>
        {/* 추천 */}
        <section>
          <PlaceContainer />
        </section>
      </div>
    </div>
  );
};

export default MyPage;
