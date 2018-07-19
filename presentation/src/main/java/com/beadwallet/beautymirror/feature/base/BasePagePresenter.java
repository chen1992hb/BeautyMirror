package com.beadwallet.beautymirror.feature.base;

import com.beadwallet.domain.interactor.UseCase;


public abstract class BasePagePresenter extends BasePresenter{

  public BasePagePresenter(UseCase useCase) {
    super(useCase);
  }

  public abstract void refresh();

  public abstract void loadMore();
}
