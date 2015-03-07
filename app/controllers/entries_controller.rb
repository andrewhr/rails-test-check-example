class EntriesController < ApplicationController
  before_action :fetch_all_entries

  def index
    @entry = Entry.new
  end

  def create
    @entry = Entry.create(entry_params)
    if @entry.valid?
      redirect_to entries_path
    else
      render :index
    end
  end

  def destroy
    Entry.find(params.fetch(:id)).destroy!
    redirect_to entries_path
  end

  private

  def entry_params
    params.require(:entry).permit(:value)
  end

  def fetch_all_entries
    @entries = Entry.all
  end
end
