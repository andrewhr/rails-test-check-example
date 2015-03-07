Rails.application.routes.draw do
  namespace :api do
    resources :entries, only: [:create, :destroy]
  end

  resources :entries

  root to: "entries#index"
end
